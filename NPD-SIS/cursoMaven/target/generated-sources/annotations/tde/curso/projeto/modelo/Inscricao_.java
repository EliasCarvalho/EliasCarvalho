package tde.curso.projeto.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Inscricao.class)
public abstract class Inscricao_ {

	public static volatile SingularAttribute<Inscricao, Date> dtInscricao;
	public static volatile SingularAttribute<Inscricao, Integer> cdPessoa;
	public static volatile SingularAttribute<Inscricao, Integer> nuFrequencia;
	public static volatile SingularAttribute<Inscricao, String> fgCertificado;
	public static volatile SingularAttribute<Inscricao, Long> cdInscricao;
	public static volatile SingularAttribute<Inscricao, Integer> cdCurso;

}

