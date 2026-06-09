/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.NamedEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

/**
 * Simple business object representing a pet.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Wick Dynex
 */
@Entity
@Table(name = "pets")
public class Pet extends NamedEntity {

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	// The pet's birth date, formatted as YYYY-MM-DD
	private LocalDate birthDate;

	@ManyToOne
	@JoinColumn(name = "type_id")
	// The category type of pet (e.g. cat, dog, lizard)
	private PetType type;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pet_id")
	@OrderBy("date ASC")
	// Eagerly loaded set of historical medical visits, sorted chronologically
	private final Set<Visit> visits = new LinkedHashSet<>();

	/**
	 * Sets the birth date of the pet.
	 * @param birthDate birth date
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the birth date of the pet.
	 * @return birth date
	 */
	public LocalDate getBirthDate() {
		return this.birthDate;
	}

	/**
	 * Gets the pet type.
	 * @return pet type entity
	 */
	public PetType getType() {
		return this.type;
	}

	/**
	 * Sets the pet type.
	 * @param type pet type entity
	 */
	public void setType(PetType type) {
		this.type = type;
	}

	/**
	 * Gets the set of visits.
	 * @return collection of visits
	 */
	public Collection<Visit> getVisits() {
		return this.visits;
	}

	/**
	 * Adds a new visit record.
	 * @param visit visit record to add
	 */
	public void addVisit(Visit visit) {
		getVisits().add(visit);
	}

}
