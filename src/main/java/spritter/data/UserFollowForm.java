package spritter.data;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserFollowForm {
	
	//@NotNull(message = "You must be logged in to do that")
	private Long userId;
	
	@NotNull(message = "Please select a user to follow")
	private Long followsId;

}
