package fastcampus.ad.migration.gradual.domain.recentad.keyword;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentKeywordRepository extends CrudRepository<RecentKeyword, Long> {

}
