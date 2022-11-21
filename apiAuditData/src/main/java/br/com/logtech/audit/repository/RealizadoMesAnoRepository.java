package br.com.logtech.audit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.logtech.audit.model.RealizadoMesAno;
import br.com.logtech.audit.projection.RealizadoAnoProjection;

@Repository
public interface RealizadoMesAnoRepository extends JpaRepository<RealizadoMesAno, Long>{

	List<RealizadoMesAno> findByAno(Integer ano);
	
	@Query(nativeQuery = true,
			value="SELECT Coalesce((SELECT valor"
					+ "      FROM tb_meta_mes mt"
					+ "		 WHERE mt.ano = :ano"
					+ "        AND mt.mes = :mes"
					+ "        AND mt.codigo = :codigo),0) as previsto,"
					+ "	   Coalesce((SELECT valor"
					+ "       FROM tb_realizado_mes_ano ma"
					+ "		 WHERE ma.ano = :ano"
					+ "        AND ma.mes = :mes"
					+ "        AND ma.codigo = :codigo),0) as realizado"
			)
	RealizadoAnoProjection findRealziadoByMesAnoCodigo(Integer ano, Integer mes, String codigo);

	@Query(nativeQuery = true,
			value="SELECT Coalesce((SELECT SUM(valor)"
					+ "  FROM tb_meta_mes mt"
					+ "	WHERE mt.ano = :ano"
					+ "   AND mt.mes = :mes"
					+ "   AND mt.codigo IN (SELECT codigo FROM tb_config_gestao "
					+ "                      WHERE grupo = :grupo AND soma = true)),0) as previsto,"
					+ "  Coalesce((SELECT SUM(valor)"
					+ "   FROM tb_realizado_mes_ano mt"
					+ "	 WHERE mt.ano = :ano"
					+ "    AND mt.mes = :mes"
					+ "    AND mt.codigo IN (SELECT codigo FROM tb_config_gestao "
					+ "                       WHERE grupo = :grupo AND soma = true)),0) as realizado"
			)
	RealizadoAnoProjection SomaRealizadoByMesAnoCodigo(Integer ano, Integer mes, Integer grupo);
	
	@Query(nativeQuery = true,
			value="SELECT Coalesce((SELECT SUM(valor)"
					+ "               FROM tb_meta_mes mt"
					+ "	             WHERE mt.ano = :ano"
					+ "                AND mt.mes = :mes"
					+ "                AND mt.codigo IN (SELECT codigo "
					+ "                                    FROM tb_config_gestao "
					+ "                                   WHERE grupo = 1 AND soma = true)) - "
					+ "            (SELECT SUM(valor)"
					+ "				  FROM tb_meta_mes mt"
					+ "				 WHERE mt.ano = :ano"
					+ "				   AND mt.mes = :mes"
					+ "				   AND mt.codigo IN (SELECT codigo "
					+ "					                   FROM tb_config_gestao"
					+ "					                  WHERE grupo = 2 AND soma = true)),0) as previsto,"
					+ "   Coalesce((SELECT SUM(valor)"
					+ "               FROM tb_realizado_mes_ano mt"
					+ "	             WHERE mt.ano = :ano"
					+ "                AND mt.mes = :mes"
					+ "                AND mt.codigo IN (SELECT codigo "
					+ "                                    FROM tb_config_gestao "
					+ "                                   WHERE grupo = 1 AND soma = true)) - "
					+ "            (SELECT SUM(valor)"
					+ "				  FROM tb_realizado_mes_ano mt"
					+ "				 WHERE mt.ano = :ano"
					+ "				   AND mt.mes = :mes"
					+ "				   AND mt.codigo IN (SELECT codigo "
					+ "					                   FROM tb_config_gestao"
					+ "					                  WHERE grupo = 2 AND soma = true)),0) as realizado"
			)
	RealizadoAnoProjection SaldoEntradaSaidaByMesAnoCodigo(Integer ano, Integer mes);

	@Transactional(readOnly = true)
	RealizadoMesAno findByMesAndAnoAndCodigo(Integer mes, Integer ano, String codigo);
}
