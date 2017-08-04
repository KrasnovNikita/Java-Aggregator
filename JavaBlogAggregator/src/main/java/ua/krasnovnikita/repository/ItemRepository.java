package ua.krasnovnikita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.krasnovnikita.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
