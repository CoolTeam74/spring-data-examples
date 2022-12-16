package ru.apolyakov.ui_custom_filters.service;

import com.google.common.collect.Sets;
import ru.apolyakov.ui_custom_filters.dto.Filter;
import ru.apolyakov.ui_custom_filters.dto.SearchCriteria;
import ru.apolyakov.ui_custom_filters.entity.ReferenceInfo;
import ru.apolyakov.ui_custom_filters.entity.Widget;
import ru.apolyakov.ui_custom_filters.entity.Workspace;
import ru.apolyakov.ui_custom_filters.repository.ReferenceInfoRepository;
import ru.apolyakov.ui_custom_filters.repository.WorkspaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.Instant;

@SpringBootTest
@Transactional
public class SearchServiceTest {
    @Autowired
    private ReferenceInfoRepository referenceInfoRepository;
    @Autowired
    private SearchService searchService;
    @Autowired
    private WorkspaceRepository workspaceRepository;

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

        Widget widget = Widget.builder()
                .name("test")
                .parameters("")
                .layout("")
                .build();

        Workspace workspace = Workspace.builder()
                .createTime(Instant.now())
                .widgets(Sets.newHashSet(widget))
                .name("workspace")
                .build();
        workspaceRepository.save(workspace);

    }

    @AfterEach
    public void tearUp() {
        referenceInfoRepository.delete(workspaceRepositoryReferenceInfo);
        referenceInfoRepository.delete(widgetRepositoryReferenceInfo);
    }

    @Test
    public void testSearchWorkspacesByWidgetName() {
        Filter filter = SearchCriteria.builder()
                .key("name")
                .operator("=")
                .value("test")
                .table("widgets")
                .build();
        String itemType = "workspace";

        searchService.search(filter, itemType);
    }
}
