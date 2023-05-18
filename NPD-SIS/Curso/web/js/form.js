/*************************************************************************
//                        VERIFICAÇÃO DE CPF/CNPJ
**************************************************************************/

/**
 * @author M�rcio d'�vila
 * @version 1.01, 2004
 *
 * PROT�TIPOS:
 * m�todo String.lpad(int pSize, char pCharPad)
 * m�todo String.trim()
 *
 * String unformatNumber(String pNum)
 * String formatCpfCnpj(String pCpfCnpj, boolean pUseSepar, boolean pIsCnpj)
 * String dvCpfCnpj(String pEfetivo, boolean pIsCnpj)
 * boolean isCpf(String pCpf)
 * boolean isCnpj(String pCnpj)
 * boolean isCpfCnpj(String pCpfCnpj)
 */


NUM_DIGITOS_CPF  = 11;
NUM_DIGITOS_CNPJ = 14;
NUM_DGT_CNPJ_BASE = 8;


/**
 * Adiciona m�todo lpad() � classe String.
 * Preenche a String � esquerda com o caractere fornecido,
 * at� que ela atinja o tamanho especificado.
 */
String.prototype.lpad = function(pSize, pCharPad)
{
	var str = this;
	var dif = pSize - str.length;
	var ch = String(pCharPad).charAt(0);
	for (; dif>0; dif--) str = ch + str;
	return (str);
} //String.lpad


/**
 * Adiciona m�todo trim() � classe String.
 * Elimina brancos no in�cio e fim da String.
 */
String.prototype.trim = function()
{
	return this.replace(/^\s*/, "").replace(/\s*$/, "");
} //String.trim


/**
 * Elimina caracteres de formata��o e zeros � esquerda da string
 * de n�mero fornecida.
 * @param String pNum
 * 	String de n�mero fornecida para ser desformatada.
 * @return String de n�mero desformatada.
 */
function unformatNumber(pNum)
{
	return String(pNum).replace(/\D/g, "").replace(/^0+/, "");
} //unformatNumber


/**
 * Formata a string fornecida como CNPJ ou CPF, adicionando zeros
 * � esquerda se necess�rio e caracteres separadores, conforme solicitado.
 * @param String pCpfCnpj
 * 	String fornecida para ser formatada.
 * @param boolean pUseSepar
 * 	Indica se devem ser usados caracteres separadores (. - /).
 * @param boolean pIsCnpj
 * 	Indica se a string fornecida � um CNPJ.
 * 	Caso contr�rio, � CPF. Default = false (CPF).
 * @return String de CPF ou CNPJ devidamente formatada.
 */
function formatCpfCnpj(pCpfCnpj, pUseSepar, pIsCnpj)
{
	if (pIsCnpj==null) pIsCnpj = false;
	if (pUseSepar==null) pUseSepar = true;
	var maxDigitos = pIsCnpj? NUM_DIGITOS_CNPJ: NUM_DIGITOS_CPF;
	var numero = unformatNumber(pCpfCnpj);

	numero = numero.lpad(maxDigitos, '0');
	if (!pUseSepar) return numero;

	if (pIsCnpj)
	{
		reCnpj = /(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/;
		numero = numero.replace(reCnpj, "$1.$2.$3/$4-$5");
	}
	else
	{
		reCpf  = /(\d{3})(\d{3})(\d{3})(\d{2})$/;
		numero = numero.replace(reCpf, "$1.$2.$3-$4");
	}
	return numero;
} //formatCpfCnpj


/**
 * Calcula os 2 d�gitos verificadores para o n�mero-efetivo pEfetivo de
 * CNPJ (12 d�gitos) ou CPF (9 d�gitos) fornecido. pIsCnpj � booleano e
 * informa se o n�mero-efetivo fornecido � CNPJ (default = false).
 * @param String pEfetivo
 * 	String do n�mero-efetivo (SEM d�gitos verificadores) de CNPJ ou CPF.
 * @param boolean pIsCnpj
 * 	Indica se a string fornecida � de um CNPJ.
 * 	Caso contr�rio, � CPF. Default = false (CPF).
 * @return String com os dois d�gitos verificadores.
 */
function dvCpfCnpj(pEfetivo, pIsCnpj)
{
	if (pIsCnpj==null) pIsCnpj = false;
	var i, j, k, soma, dv;
	var cicloPeso = pIsCnpj? NUM_DGT_CNPJ_BASE: NUM_DIGITOS_CPF;
	var maxDigitos = pIsCnpj? NUM_DIGITOS_CNPJ: NUM_DIGITOS_CPF;
	var calculado = formatCpfCnpj(pEfetivo, false, pIsCnpj);
	calculado = calculado.substring(2, maxDigitos);
	var result = "";

	for (j = 1; j <= 2; j++)
	{
		k = 2;
		soma = 0;
		for (i = calculado.length-1; i >= 0; i--)
		{
			soma += (calculado.charAt(i) - '0') * k;
			k = (k-1) % cicloPeso + 2;
		}
		dv = 11 - soma % 11;
		if (dv > 9) dv = 0;
		calculado += dv;
		result += dv
	}

	return result;
} //dvCpfCnpj


/**
 * Testa se a String pCpf fornecida � um CPF v�lido.
 * Qualquer formata��o que n�o seja algarismos � desconsiderada.
 * @param String pCpf
 * 	String fornecida para ser testada.
 * @return <code>true</code> se a String fornecida for um CPF v�lido.
 */
function isCpf(pCpf)
{
	var numero = formatCpfCnpj(pCpf, false, false);
	var base = numero.substring(0, numero.length - 2);
	var digitos = dvCpfCnpj(base, false);
	var algUnico, i;

	// Valida d�gitos verificadores
	if (numero != base + digitos) return false;

	/* N�o ser�o considerados v�lidos os seguintes CPF:
	 * 000.000.000-00, 111.111.111-11, 222.222.222-22, 333.333.333-33, 444.444.444-44,
	 * 555.555.555-55, 666.666.666-66, 777.777.777-77, 888.888.888-88, 999.999.999-99.
	 */
	algUnico = true;
	for (i=1; i<NUM_DIGITOS_CPF; i++)
	{
		algUnico = algUnico && (numero.charAt(i-1) == numero.charAt(i));
	}
	return (!algUnico);
} //isCpf


/**
 * Testa se a String pCnpj fornecida � um CNPJ v�lido.
 * Qualquer formata��o que n�o seja algarismos � desconsiderada.
 * @param String pCnpj
 * 	String fornecida para ser testada.
 * @return <code>true</code> se a String fornecida for um CNPJ v�lido.
 */
function isCnpj(pCnpj)
{
	var numero = formatCpfCnpj(pCnpj, false, true);
	var base = numero.substring(0, NUM_DGT_CNPJ_BASE);
	var ordem = numero.substring(NUM_DGT_CNPJ_BASE, 12);
	var digitos = dvCpfCnpj(base + ordem, true);
	var algUnico;

	// Valida d�gitos verificadores
	if (numero != base + ordem + digitos) return false;

	/* N�o ser�o considerados v�lidos os CNPJ com os seguintes n�meros B�SICOS:
	 * 11.111.111, 22.222.222, 33.333.333, 44.444.444, 55.555.555,
	 * 66.666.666, 77.777.777, 88.888.888, 99.999.999.
	 */
	algUnico = numero.charAt(0) != '0';
	for (i=1; i<NUM_DGT_CNPJ_BASE; i++)
	{
		algUnico = algUnico && (numero.charAt(i-1) == numero.charAt(i));
	}
	if (algUnico) return false;

	/* N�o ser� considerado v�lido CNPJ com n�mero de ORDEM igual a 0000.
	 * N�o ser� considerado v�lido CNPJ com n�mero de ORDEM maior do que 0300
	 * e com as tr�s primeiras posi��es do n�mero B�SICO com 000 (zeros).
	 * Esta cr�tica n�o ser� feita quando o no B�SICO do CNPJ for igual a 00.000.000.
	 */
	if (ordem == "0000") return false;
	return (base == "00000000"
		|| parseInt(ordem, 10) <= 300 || base.substring(0, 3) != "000");
} //isCnpj


/**
 * Testa se a String pCpfCnpj fornecida � um CPF ou CNPJ v�lido.
 * Se a String tiver uma quantidade de d�gitos igual ou inferior
 * a 11, valida como CPF. Se for maior que 11, valida como CNPJ.
 * Qualquer formata��o que n�o seja algarismos � desconsiderada.
 * @param String pCpfCnpj
 * 	String fornecida para ser testada.
 * @return <code>true</code> se a String fornecida for um CPF ou CNPJ v�lido.
 */
function isCpfCnpj(pCpfCnpj)
{
	var numero = pCpfCnpj.replace(/\D/g, "");
	if (numero.length > NUM_DIGITOS_CPF)
		return isCnpj(pCpfCnpj)
	else
		return isCpf(pCpfCnpj);
} //isCpfCnpj


/**************************************************************************
Fun��o para simular um Tab quando for pressionado a tecla Enter
Exemplo: onKeyDown="TABEnter()"
Funciona em TEXT BOX,RADIO BUTTON, CHECK BOX e menu DROP-DOWN
**************************************************************************/
function TABEnter(oEvent){
  var oEvent = (oEvent)? oEvent : event;
  var oTarget =(oEvent.target)? oEvent.target : oEvent.srcElement;
  if(oEvent.keyCode==13)
    oEvent.keyCode = 9;
  if(oTarget.type=="text" && oEvent.keyCode==13)
    //return false;
    oEvent.keyCode = 9;
  if (oTarget.type=="radio" && oEvent.keyCode==13)
    oEvent.keyCode = 9;
}

// create the prototype on the String object
String.prototype.trim = function() {
    // skip leading and trailing whitespace
    // and return everything in between
    return this.replace(/^\s*(\b.*\b|)\s*$/, "$1");
}

// create the prototype on the String object
String.prototype.trimLeadingZeros = function(todos) { //true, false
    if (""+todos=="undefined") todos=false;
    
    //tirando os zeros do come�o
    var i=0;
    while ((i < this.length- (todos?0:1) ) && (this.substring(i,i+1)=='0')) i++;
    valor = this.substring(i);
    return valor;
}

function stripCharsNotInBag(bag, campo) { //campo s� deve ser passado se for para alterar seu valor
    //bag = "0123456789";
    
    var temp="";
    if (campo==null) temp=this;
    if (campo!=null) temp=campo.value;
    
    var result = "";
    for (i=0; i<temp.length; i++){
        character = temp.charAt(i);
        if (bag.indexOf(character) != -1)
            result += character;
    }
    if (campo!=null && campo.value!=result) {
        campo.value=result;
    }
    return result;
}

// create the prototype on the String object
String.prototype.stripCharsNotInBag = stripCharsNotInBag;

function stripNotNumber(num) {
    return num.stripCharsNotInBag("0123456789");
}

function stripNotNumberCpfCnpj(num) {
    return num.stripCharsNotInBag("0123456789.-/");
}

var BASE_DATE = new Date("1997","09","07")  // 1999-out-07
var MAX_DATE = new Date("2025","01","21")   // 2025-fev-21

function ValidaData(data) {
    dt = data.value;
    
    if (dt.length<10) {
        alert("Tamanho incorreto, digitar no formato dd/mm/aaaa.");
        data.select();
        return false;
    }
    
    dia = dt.substring(0,2);
    mes = dt.substring(3,5);
    ano = dt.substring(6,10);
    
    // month argument must be in the range 1 - 12
    // javascript month range : 0- 11
    var tempDate = new Date(ano,mes-1,dia);
    
    if ( (ano == tempDate.getFullYear()) &&
    (mes == (tempDate.getMonth()+1)) &&
    (dia == tempDate.getDate()) ) {
        var tmp = new Date();
        var todayDate = new Date(tmp.getFullYear(), tmp.getMonth(), tmp.getDate());
        
        //return (tempDate >= BASE_DATE && tempDate<=MAX_DATE && tempDate>=todayDate)
        return true;
    } else {
        alert("Data incorreta, digitar no formato dd/mm/aaaa.");
        data.select();
        return false;
    }
    return true;
}


function formataDataDigitada(campo) {
    // retira tudo que nao eh numerico
    var temp=campo.value;
    var valor="";
    
    valor=stripNotNumber(temp);
    
    if (valor.length>8) { valor=valor.substring(0,8); }
    
    var j=0;
    temp="";
    for (var tam=0;tam<valor.length;tam++) {
        if (j==0) {
            temp+=valor.substring(tam,tam+1);
            if ( (tam==1) && (valor.length>2) ) { j++; temp+="/"; }
        } else if (j==1) {
            temp+=valor.substring(tam,tam+1);
            if ( (tam==3) && (valor.length>4) ) { j++; temp+="/"; }
        } else if (j==2) {
            temp+=valor.substring(tam,tam+1);
        }
    }
    
    if (campo.value!=temp) {
        campo.value=temp;
    }
}



function FormataNumero(num,decimalNum,bolLeadingZero,bolParens,bolCommas)
/**********************************************************************
 IN:
 NUM - the number to format
 decimalNum - the number of decimal places to format the number to
 bolLeadingZero - true / false - display a leading zero for
 numbers between -1 and 1
 bolParens - true / false - use parenthesis around negative numbers
 bolCommas - put commas as number separators.
 
 RETVAL:
 The formatted number!
 **********************************************************************/
{
    if (isNaN(parseInt(num))) return "NaN";
    
    var tmpNum = num;
    var iSign = num < 0 ? -1 : 1;                // Get sign of number
    
    // Adjust number so only the specified number of numbers after
    // the decimal point are shown.
    tmpNum *= Math.pow(10,decimalNum);
    tmpNum = Math.round(Math.abs(tmpNum))
    tmpNum /= Math.pow(10,decimalNum);
    tmpNum *= iSign;                                        // Readjust for sign
    
    // Create a string object to do our formatting on
    var tmpNumStr = new String(tmpNum);
    
    // See if we need to strip out the leading zero or not.
    if (!bolLeadingZero && num < 1 && num > -1 && num != 0)
        if (num > 0)
            tmpNumStr = tmpNumStr.substring(1,tmpNumStr.length);
        else
            tmpNumStr = "-" + tmpNumStr.substring(2,tmpNumStr.length);
    
    tmpNumStr = tmpNumStr.replace(/\./g,",");
    
    
    // Complete all decimal places
    if (decimalNum>0) {
        var iStart = tmpNumStr.indexOf(",");
        if (iStart < 0) {
            tmpNumStr+=",";
            iStart = tmpNumStr.indexOf(",");
        }
        
        for (i=(decimalNum-(tmpNumStr.length-iStart)); i>=0 ; i--) {
            tmpNumStr+="0";
        }
    }
    
    
    // See if we need to put in the commas
    if (bolCommas && (num >= 1000 || num <= -1000)) {
        var iStart = tmpNumStr.indexOf(",");
        if (iStart < 0)
            iStart = tmpNumStr.length;
        
        iStart -= 3;
        while (iStart >= 1) {
            tmpNumStr = tmpNumStr.substring(0,iStart) + "." + tmpNumStr.substring(iStart,tmpNumStr.length)
            iStart -= 3;
        }
    }
    
    // See if we need to use parenthesis
    if (bolParens && num < 0)
        tmpNumStr = "(" + tmpNumStr.substring(1,tmpNumStr.length) + ")";
    
    return tmpNumStr;                // Return our formatted string!
}

function formataValorDigitado(campo, decimal) {
    var decimalNum=2;
    if (decimal!=null)
        decimalNum=decimal;
    
    var temp = FormataNumero(campo.value.stripCharsNotInBag("0123456789").trimLeadingZeros() / Math.pow(10,decimalNum), decimalNum, true, false, true);
    
    if (campo.value!=temp) {
        campo.value=temp;
    }
}

function formataValorInteiro(campo, event) {
    var temp=campo.value;
    var valor="";
    
    valor=stripNotNumber(temp);
    
    if (campo.value!=valor) {
        campo.value=valor;
    }
}

function Valido(texto,valores) {
    var valido = true;
    
    for (var i = 0;  i < texto.length;  i++) {
        var ch = texto.charAt(i);
        
        for (var j = 0;  j < valores.length;  j++)
            if (ch == valores.charAt(j))
                break;
        
        if (j == valores.length) {
            valido = false;
            break;
        }
    }
    return(valido);
}


/**
 * Funcao que mascara o valor CEP.
 * Valor retornado com separador "-"
 * Ex.: 12345-678
 */

function MascaraCEP(keypress, valorCEP) {
    caracteres = '01234567890';
    separacoes = 1;
    separacao1 = '-';
    conjuntos = 2;
    conjunto1 = 5;
    conjunto2 = 3;
    if ( (caracteres.search(String.fromCharCode(keypress))!=-1)
    && (valorCEP.value.length < (conjunto1 + conjunto2 + 1)) ){
        if (valorCEP.value.length == conjunto1)
            valorCEP.value = valorCEP.value + separacao1;
    }
    else {
        event.returnValue = false;
    }
}


// dado um objeto, verifica se este eh um numero
function verificaDigito(obj){
    string = obj.value;
    
    if (!numero(string))
        obj.value = obj.value.substring(0, obj.value.length - 1);
    return;
}


// funcao que verifica se dado um string eh string numerico
function numero(string){
    if (!string) return false;
    var Chars = "0123456789";
    
    for (var i = 0; i < string.length; i++) {
        if (Chars.indexOf(string.charAt(i)) == -1)
            return false;
    }
    return true;
}


function MascaraCNPJ(cnpj, event){
    var wTecla, wVr, wTam, pTamMax,pPos1,pPos2, pPosTraco;
    var temp=cnpj.value;

    var valor="";
    valor=stripNotNumberCpfCnpj(temp);
    
    pTamMax = 14; 
    pPos1 =12;
    pPos2 = 3;
    pPosTraco =2;
    
    wTecla = event.keyCode;
    
    wVr = valor;
    wVr = wVr.toString().replace( "-", "" );
    wVr = wVr.toString().replace( ".", "" );
    wVr = wVr.toString().replace( ".", "" );
    wVr = wVr.toString().replace( "/", "" );
    wTam = wVr.length ;
    
    if (wTam < pTamMax && wTecla != 8) { 
        wTam = wVr.length + 1 ; 
    }
    
    if (wTecla == 8 ) { 
        wTam = wTam - 1 ; 
    }
    
    if ( wTecla == 8 || wTecla == 88 || wTecla >= 48 && wTecla <= 57 || wTecla >= 96 && wTecla <= 105 ){
        if ( wTam <= 2 ){
            pForm[pCampo].value = wVr ;
        }
        if (wTam > pPosTraco && wTam <= pTamMax) {
            wVr = wVr.substr(0, wTam - pPosTraco) + '-' + wVr.substr(wTam - pPosTraco, wTam);
        }
        if ( wTam == pTamMax){
            wVr = wVr.substr( 0, 2 ) + '.' + wVr.substr(2, 3) + '.' + wVr.substr(5, 3) + '/' + wVr.substr(8, 7);
        }
       valor = wVr;
    }
     
    if (cnpj.value!=valor) {
        cnpj.value=valor;
    }
    
}


//adiciona mascara de cep
function MascaraCep(cep, event){
    var valor=cep.value;
    valor=stripNotNumberCpfCnpj(valor);
    cep.value = valor;
    return formataCampo(cep, '00000-000', event);
}

//adiciona mascara de data
function MascaraData(data, event){
    var valor=data.value;
    valor=stripNotNumberCpfCnpj(valor);
    data.value = valor;
    return formataCampo(data, '00/00/0000', event);
}

//adiciona mascara ao telefone
function MascaraTelefone(tel, event){
    var valor=tel.value;
    valor=stripNotNumberCpfCnpj(valor);
    tel.value = valor;
    return formataCampo(tel, '(00) 0000-0000', event);
}
//adiciona mascara ao CPF
function MascaraCPF(cpf, event){
    var wTecla, wVr, wTam, pTamMax,pPos1,pPos2, pPosTraco;
    var temp=cpf.value;
    var valor="";
    
    valor=stripNotNumberCpfCnpj(temp);
    
    pTamMax = 11;
    pPos1 =8;
    pPos2 =5;
    pPosTraco =2;
    
    wTecla = event.keyCode;
    
    wVr = valor;
    wVr = wVr.toString().replace( "-", "" );
    wVr = wVr.toString().replace( ".", "" );
    wVr = wVr.toString().replace( ".", "" );
    wVr = wVr.toString().replace( "/", "" );
    wTam = wVr.length ;
    
    if (wTam < pTamMax && wTecla != 8) { 
        wTam = wVr.length + 1 ; 
    }
    
    if (wTecla == 8 ) { 
        wTam = wTam - 1 ; 
    }
    
    if ( wTecla == 8 || wTecla == 88 || wTecla >= 48 && wTecla <= 57 || wTecla >= 96 && wTecla <= 105 ){
        if ( wTam <= 2 ){
            pForm[pCampo].value = wVr ;
        }
        if (wTam > pPosTraco && wTam <= pTamMax) {
            wVr = wVr.substr(0, wTam - pPosTraco) + '-' + wVr.substr(wTam - pPosTraco, wTam);
        }
        if ( wTam == pTamMax){
            wVr = wVr.substr( 0, wTam - pPos1 ) + '.' + wVr.substr(wTam - pPos1, 3) + '.' + wVr.substr(wTam - pPos2, wTam);
        }
       valor = wVr;
    }
     
    if (cpf.value!=valor) {
        cpf.value=valor;
    }
    
}

//valida telefone
function ValidaTelefone(tel){
    exp = /\(\d{2}\)\ \d{4}\-\d{4}/
    if(!exp.test(tel.value))
        alert('Numero de Telefone Invalido!');
}

//valida CEP
function ValidaCep(cep){
    exp = /\d{2}\.\d{3}\-\d{3}/
    if(!exp.test(cep.value))
        alert('Numero de Cep Invalido!');
}

//valida data
/*
function ValidaData(data){
    exp = /\d{2}\/\d{2}\/\d{4}/
    if(!exp.test(data.value)) {
        alert('Data Invalida!');
        return false;
        }
    return true;
}
*/

//valida o CPF digitado
function ValidaCPF(Objcpf){
    var cpf = Objcpf.value;
    exp = /\.|\-/g
    cpf = cpf.toString().replace( exp, "" );
    var digitoDigitado = eval(cpf.charAt(9)+cpf.charAt(10));
    var soma1=0, soma2=0;
    var vlr =11;
    
    for(i=0;i<9;i++){
        soma1+=eval(cpf.charAt(i)*(vlr-1));
        soma2+=eval(cpf.charAt(i)*vlr);
        vlr--;
    }
    soma1 = (((soma1*10)%11)==10 ? 0:((soma1*10)%11));
    soma2=(((soma2+(2*soma1))*10)%11);
    
    var digitoGerado=(soma1*10)+soma2;
    if(digitoGerado!=digitoDigitado) {
        alert('CPF Invalido!');
        return false;
        }
}

//valida numero inteiro com mascara
function mascaraInteiro(temp){
    var valor = "";
    valor=stripNotNumberCpfCnpj(temp);
    return valor;
}

//valida o CNPJ digitado
function ValidaCNPJ(ObjCnpj){
    var cnpj = ObjCnpj.value;
    var valida = new Array(6,5,4,3,2,9,8,7,6,5,4,3,2);
    var dig1= new Number;
    var dig2= new Number;
    
    exp = /\.|\-|\//g
    cnpj = cnpj.toString().replace( exp, "" );
    var digito = new Number(eval(cnpj.charAt(12)+cnpj.charAt(13)));
    
    for(i = 0; i<valida.length; i++){
        dig1 += (i>0? (cnpj.charAt(i-1)*valida[i]):0);
        dig2 += cnpj.charAt(i)*valida[i];
    }
    dig1 = (((dig1%11)<2)? 0:(11-(dig1%11)));
    dig2 = (((dig2%11)<2)? 0:(11-(dig2%11)));
    
    if(((dig1*10)+dig2) != digito) {
        alert('CNPJ Invalido!');
        return false;
        }
    
}

//formata de forma generica os campos
function formataCampo(campo, Mascara, evento) {
    var boleanoMascara;
    
    var Digitato = evento.keyCode;
    
    
    
    exp = /\-|\.|\/|\(|\)| /g
    campoSoNumeros = campo.value.toString().replace( exp, "" );
    
    var posicaoCampo = 0;
    var NovoValorCampo="";
    var TamanhoMascara = campoSoNumeros.length;;
    
    if (Digitato != 8) { // backspace
        for(i=0; i<= TamanhoMascara; i++) {
            boleanoMascara = ((Mascara.charAt(i) == "-") || (Mascara.charAt(i) == ".") || (Mascara.charAt(i) == "/"))
            boleanoMascara = boleanoMascara || ((Mascara.charAt(i) == "(") || (Mascara.charAt(i) == ")") || (Mascara.charAt(i) == " "))
            if (boleanoMascara) {
                NovoValorCampo += Mascara.charAt(i);
                TamanhoMascara++;
            }else {
                NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
                posicaoCampo++;
            }
        }
        
        
        campo.value = NovoValorCampo;
        return NovoValorCampo;
    }else {
        return true;
    }
}

//calcular a idade de uma pessoa
//recebe a data como um string em formato portugues
//devolve um inteiro com a idade. Devolve false em caso de que a data seja incorreta ou maior que o dia atual
function calcular_idade(data,dataHoje) {
    x = data.split('/');
    h = dataHoje.split('/');
    if(x[0] > 31 || x[1] > 12 || x[2] > h[2]) {
        alert('Data de Nascimento inv&#30109;ida!');
        return 0;
    }
    anosProvisorio = h[2] - x[2];

    if(h[1] < x[1]) {
        anosProvisorio -= 1;
     }else if(h[1] == x[1]) {
        if(h[0] < x[0]) {
            anosProvisorio -= 1;
        }
     }
     return anosProvisorio;
}

