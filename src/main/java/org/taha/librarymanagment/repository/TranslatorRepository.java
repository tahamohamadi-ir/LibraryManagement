package org.taha.librarymanagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.taha.librarymanagment.model.entity.Translator;

public interface TranslatorRepository extends JpaRepository<Translator, Long>, JpaSpecificationExecutor<Translator> {

}