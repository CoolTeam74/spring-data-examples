package ru.apolyakov.ui_custom_filters.repository;

import ru.apolyakov.ui_custom_filters.entity.Widget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WidgetRepository extends JpaRepository<Widget, Long> {
}
