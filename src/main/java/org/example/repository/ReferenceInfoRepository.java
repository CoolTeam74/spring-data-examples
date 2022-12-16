package org.example.repository;

import org.example.entity.ReferenceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReferenceInfoRepository extends JpaRepository<ReferenceInfo, Long> {
    Optional<ReferenceInfo> findByTypeName(String typeName);
}
