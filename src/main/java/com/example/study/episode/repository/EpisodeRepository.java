package com.example.study.episode.repository;

import com.example.study.episode.domain.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EpisodeRepository  extends JpaRepository<Episode, UUID> {
//	 List<Episode> findAllByBookId(UUID bookId);
}
