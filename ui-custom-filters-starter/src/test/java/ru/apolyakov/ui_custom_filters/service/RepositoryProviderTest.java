package ru.apolyakov.ui_custom_filters.service;

import org.springframework.test.context.ActiveProfiles;
import ru.apolyakov.ui_custom_filters.entity.ReferenceInfo;
import ru.apolyakov.ui_custom_filters.repository.ReferenceInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.apolyakov.ui_custom_filters.service.RepositoryProvider;

import javax.transaction.Transactional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
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
