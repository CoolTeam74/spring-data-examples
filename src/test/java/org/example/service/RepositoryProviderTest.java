package org.example.service;

import org.example.entity.ReferenceInfo;
import org.example.repository.ReferenceInfoRepository;
import org.example.service.RepositoryProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class RepositoryProviderTest {
    @Autowired
    private ReferenceInfoRepository referenceInfoRepository;
    @Autowired
    private RepositoryProvider repositoryProvider;

    private ReferenceInfo widgetRepositoryReferenceInfo;
    private ReferenceInfo workspaceRepositoryReferenceInfo;

    @BeforeEach
    public void setup() {
        widgetRepositoryReferenceInfo = ReferenceInfo.builder()
                .typeName("widget")
                .repositoryName("widgetRepository")
                .build();

        referenceInfoRepository.save(widgetRepositoryReferenceInfo);

        workspaceRepositoryReferenceInfo = ReferenceInfo.builder()
                .typeName("workspace")
                .repositoryName("workspaceRepository")
                .build();

        referenceInfoRepository.save(workspaceRepositoryReferenceInfo);
    }

    @AfterEach
    public void tearUp() {
        referenceInfoRepository.delete(workspaceRepositoryReferenceInfo);
        referenceInfoRepository.delete(widgetRepositoryReferenceInfo);
    }

    @Test
    void testRepositoryProvider() {
        JpaSpecificationExecutor repository = repositoryProvider.getRepository("workspace");
        Assertions.assertNotNull(repository);

        repository = repositoryProvider.getRepository("widget");
        Assertions.assertNull(repository);
    }
}
