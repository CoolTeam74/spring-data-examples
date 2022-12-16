package ru.apolyakov.ui_custom_filters.repository;

import ru.apolyakov.ui_custom_filters.entity.ReferenceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReferenceInfoRepository extends JpaRepository<ReferenceInfo, Long> {
    Optional<ReferenceInfo> findByTypeName(String typeName);
}
