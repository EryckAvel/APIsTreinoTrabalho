package br.com.logtech.audit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.logtech.audit.model.Movimentacao;
import br.com.logtech.audit.model.PlanoConta;
import br.com.logtech.audit.projection.PlanoContaValoresProjection;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

	@Query(nativeQuery = true,
			value="SELECT mp.mes, mp.plano_codigo,"
					+ "   (SELECT Coalesce(mpd.valor,0)"
					+ "      FROM tb_movimentacao_prevista mpd"
					+ "	    WHERE mpd.plano_codigo = mp.plano_codigo"
					+ "       AND mpd.mes = mp.mes"
					+ "       AND mpd.ano = mp.ano) as previsto,"
					+ "   (SELECT Coalesce(mpd.valor,0)"
					+ "      FROM tb_movimentacao mpd"
					+ "	    WHERE mpd.plano_codigo = mp.plano_codigo"
					+ "       AND mpd.mes = mp.mes"
					+ "       AND mpd.ano = mp.ano) as realizado"
					+ "  FROM tb_movimentacao mp"
					+ " WHERE mp.ano = :ano"
					+ "   AND mp.plano_codigo = :codigo"
			)
	List<PlanoContaValoresProjection> findMovByAnoCodigo(Integer ano, String codigo);

	Movimentacao findByAnoAndMesAndPlano(Integer ano, Integer mes, PlanoConta plano);
	
}
