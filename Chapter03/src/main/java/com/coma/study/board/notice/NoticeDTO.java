package com.coma.study.board.notice;

import com.coma.study.board.BoardDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "notice")
public class NoticeDTO extends BoardDTO {}
