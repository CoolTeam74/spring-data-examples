package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.ReferenceInfo;
import org.example.repository.ReferenceInfoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RepositoryProvider {
    private final Map<String, ? extends JpaSpecificationExecutor> repositories;
    private final ReferenceInfoRepository referenceInfoRepository;

    @PostConstruct
    public void updateReferences() {
        // TODO: add REFERENCE_INFO table update and sync
    }

    public JpaSpecificationExecutor getRepository(String itemType) {
        Optional<ReferenceInfo> referenceInfoOptional = referenceInfoRepository.findByTypeName(itemType);
        return referenceInfoOptional
                .map(referenceInfo -> repositories.get(referenceInfo.getRepositoryName()))
                .orElse(null);
    }
}
