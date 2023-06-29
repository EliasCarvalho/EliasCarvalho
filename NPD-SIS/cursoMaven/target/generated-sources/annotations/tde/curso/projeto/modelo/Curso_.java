package tde.curso.projeto.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Curso.class)
public abstract class Curso_ {

	public static volatile SingularAttribute<Curso, Date> dtCursoFim;
	public static volatile SingularAttribute<Curso, Date> dtInscricaoFim;
	public static volatile SingularAttribute<Curso, Short> nuCarga;
	public static volatile SingularAttribute<Curso, String> nmAnexo;
	public static volatile SingularAttribute<Curso, Short> tpCurso;
	public static volatile SingularAttribute<Curso, Short> nuVagas;
	public static volatile SingularAttribute<Curso, String> deTitulo;
	public static volatile SingularAttribute<Curso, Date> dtInscricaoInicio;
	public static volatile SingularAttribute<Curso, Long> cdCurso;
	public static volatile SingularAttribute<Curso, Date> dtCursoInicio;

}

