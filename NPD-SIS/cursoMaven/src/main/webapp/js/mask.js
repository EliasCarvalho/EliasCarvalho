function applyZipCodeMask(compId){
   applyMask(compId, "99.999-999")
}

function applyPhoneMask(compId){
   applyMask(compId, "(99)9999-9999");
}

function applyCelMask(compId){
   applyMask(compId, "(99)99999-9999");
}

function applyDotacaoMask(compId){
   applyMask(compId, "9.999.9999");
}

function applyEmpenhoMask(compId){
   applyMask(compId, "9/99999-9");
   //applyMask(compId, "999999-9/99");
}

function applySetorMask(compId){
   applyMask(compId, "99.99.99.9");
}

function applyMask(compId, mask){
   compId = '#' + compId;
   jQuery(compId).mask(mask);
}

