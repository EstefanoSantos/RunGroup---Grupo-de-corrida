package com.estefanosantos.club.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="clubs")
public class Club {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="photo_url")
	private String photoUrl;
	
	@Column(name="content")
	private String content;
	
	@Column(name="created_on")
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	@Column(name="updated_on")
	@UpdateTimestamp
	private LocalDateTime updatedOn;
	
	
	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	private UserEntity createdBy;
	
	@OneToMany(mappedBy ="club", cascade = CascadeType.REMOVE)
	private List<Event> events = new ArrayList<>();
	
	
}
