package fastcampus.ad.migration.domain.legacyad.keyword;

import fastcampus.ad.migration.gradual.domain.legacyad.keyword.LegacyKeyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegacyKeywordRepository extends CrudRepository<LegacyKeyword, Long> {

}
