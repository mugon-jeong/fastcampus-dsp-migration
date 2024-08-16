package fastcampus.ad.legacy.application.user;

import fastcampus.ad.legacy.domain.user.LegacyUser;
import fastcampus.ad.legacy.domain.user.LegacyUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LegacyUserService {

  private final LegacyUserRepository repository;

  @Transactional
  public LegacyUserResult create(LegacyUserCreateCommand command) {
    LegacyUser newUser = LegacyUser.of(command.name());
    return LegacyUserResult.from(repository.save(newUser));
  }

  public LegacyUserResult find(Long id) {
    return LegacyUserResult.from(findById(id));
  }

  private LegacyUser findById(Long id) {
    return repository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Transactional
  public LegacyUserResult update(Long id, String name) {
    LegacyUser user = findById(id);
    user.update(name);
    return LegacyUserResult.from(user);
  }

  @Transactional
  public LegacyUserResult delete(Long id) {
    LegacyUser user = findById(id);
    user.delete();
    return LegacyUserResult.from(repository.save(user));
  }
}
