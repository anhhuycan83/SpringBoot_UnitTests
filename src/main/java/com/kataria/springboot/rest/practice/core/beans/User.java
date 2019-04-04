package com.kataria.springboot.rest.practice.core.beans;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(description = "This Model represents the JSON data to be sent in request body for adding one user .")
public class User extends RestResponse implements Cloneable {

	@ApiModelProperty(value = "User ID. Not needed while adding a user.", required = false, position = 1, allowableValues = "range[1, infinity]")
	@PositiveOrZero(message = "User Id must be positive.")
	private int id;

	@ApiModelProperty(value = "User's First Name. It Cannot be null or empty .", required = true, position = 2, example = "Vaneet")
	@NotBlank(message = "First name cannot be empty.")
	private String firstName;

	@ApiModelProperty(value = "User's Date of birth. It Cannot be null , empty or a time instance in present or future . Format should be yyyy-mm-ddTHH:mm:ss.sssZ .", required = true, position = 3)
	@NotNull(message = "Date of birth cannot be null.")
	@Past(message = "Date of birth must be in Past.")
	private Date dateOfBirth;

	@ApiModelProperty(value = "User's Address . It Cannot be null . It should be a valid address as specified .", required = true, position = 4)
	@NotNull(message = "Address cannot be null.")
	@Valid
	private Address address;

	@ApiModelProperty(value = "User's Juniors . It Cannot be a null or empty list. Elements in the list cannot be null or empty . ", required = true, position = 5)
	@NotEmpty(message = "Atleast one Junior is required.")
	private List<@NotBlank(message = "Junior Name cannot be null or empty.") String> juniors;

	@ApiModelProperty(value = "User's Corressponding addresses . It Cannot be a null or empty list. All List elements must be a valid address.", required = true, position = 6)
	@NotEmpty(message = "Atleast one corressponding address is required.")
	private List<@NotNull(message = "Corresponding address cannot be null.") @Valid Address> correspondingAddresses;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String firstName, Date dateOfBirth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
	}

	public User(int id, String firstName) {
		super();
		this.id = id;
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getJuniors() {
		return juniors;
	}

	public void setJuniors(List<String> juniors) {
		this.juniors = juniors;
	}

	public List<Address> getCorrespondingAddresses() {
		return correspondingAddresses;
	}

	public void setCorrespondingAddresses(List<Address> correspondingAddresses) {
		this.correspondingAddresses = correspondingAddresses;
	}

	@ApiModel(description = "This represents user's address .")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class Address {

		@ApiModelProperty(value = "This feild cannot be null or empty . It should be a String having 10 to 100 characters.", required = true)
		@NotBlank(message = "Address cannot be null or blank.")
		@Size(min = 10, max = 100, message = "Address must be given in 10 to 100 characters.")
		private String address;

		public Address(
				@NotBlank(message = "Address cannot be null or blank.") @Size(min = 10, max = 100, message = "Address must be given in 10 to 100 characters.") String address) {
			super();
			this.address = address;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public static Address of(String address) {
			return new Address(address);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((address == null) ? 0 : address.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Address other = (Address) obj;
			if (address == null) {
				if (other.address != null)
					return false;
			} else if (!address.equals(other.address))
				return false;
			return true;
		}

	}

	// static factory methods.
	public static User of(int id, String firstName) {
		Objects.requireNonNull(firstName);
		return new User(id, firstName);
	}

	// builder helpers.
	public static class UserBuilder {
		private User user;

		public UserBuilder() {
			user = new User();
		}

		public UserBuilder id(int id) {
			this.user.id = id;
			return this;
		}

		public UserBuilder firstName(String firstName) {
			this.user.firstName = firstName;
			return this;
		}

		public UserBuilder dateOfBirth(Date dateOfBirth) {
			this.user.dateOfBirth = dateOfBirth;
			return this;
		}

		public UserBuilder address(Address address) {
			this.user.address = address;
			return this;
		}

		public UserBuilder correspondingAddresses(List<Address> correspondingAddresses) {
			this.user.correspondingAddresses = correspondingAddresses;
			return this;
		}

		public UserBuilder juniors(List<String> juniors) {
			this.user.juniors = juniors;
			return this;
		}

		public User build() {
			return this.user;
		}
	}

	public static UserBuilder id(int id) {
		return new UserBuilder().id(id);
	}

	public static UserBuilder firstName(String firstName) {
		return new UserBuilder().firstName(firstName);
	}

	public static UserBuilder dateOfBirth(Date dateOfBirth) {
		return new UserBuilder().dateOfBirth(dateOfBirth);
	}

	public static UserBuilder address(Address address) {
		return new UserBuilder().address(address);
	}

	public static UserBuilder correspondingAddresses(List<Address> correspondingAddresses) {
		return new UserBuilder().correspondingAddresses(correspondingAddresses);
	}

	public static UserBuilder juniors(List<String> juniors) {
		return new UserBuilder().juniors(juniors);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, firstName=%s, dateOfBirth=%s, getResponseStatus()=%s]", id, firstName,
				dateOfBirth);
	}

}
