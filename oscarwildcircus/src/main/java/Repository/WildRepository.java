package Repository;

import com.oscarwildcircus.Entity.Wild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WildRepository extends JpaRepository<Wild,Long > {
}
