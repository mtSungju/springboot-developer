package me.shinsungyoung.springbootdeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface CommonRepository<T,ID> extends JpaRepository<T, ID> {
}
