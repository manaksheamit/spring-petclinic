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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 * @author Dave Syer
 */
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

	@Column(name = "visit_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	// The scheduled or historical date of the clinic visit
	private LocalDate date;

	@NotBlank
	// Brief diagnostic description of why the pet was brought in
	private String description;

	/**
	 * Creates a new instance of Visit.
	 * Default date is initialized to tomorrow.
	 */
	public Visit() {
		this.date = LocalDate.now().plusDays(1);
	}

	/**
	 * Gets the visit date.
	 * @return visit date
	 */
	public LocalDate getDate() {
		return this.date;
	}

	/**
	 * Sets the visit date.
	 * @param date new visit date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Gets the description of the visit.
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the visit.
	 * @param description new description text
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
