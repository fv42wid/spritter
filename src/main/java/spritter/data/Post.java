package spritter.data;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Please enter a message")
	private String message;
	
	@ManyToOne
	private User user;
	
	private Date createdAt;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}

}
