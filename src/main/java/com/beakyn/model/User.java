package com.beakyn.model;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;


@ToString
@EqualsAndHashCode(callSuper = false, of = { "email" })
@ApiObject(name = "User")
public class User extends AbstractDocument implements AttributesDocument {

    public static final String KEY_ACTIVE = "active";
    public static final String KEY_SELECTED_GROUP_IDS = "g";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_COMPANY_IDS = "companyIds";
    public static final String KEY_FIRST_TIME_USER = "firstTimeUser";
    public static final String KEY_FOLDERS = "folders";
    public static final String KEY_GROUPS = "groups";
    public static final String KEY_LAST_LOGIN_TIME = "lastLoginTime";
    public static final String KEY_SESSION_TOKENS = "sessionTokens";
    public static final String KEY_VERIFICATION_LINK_ID = "v";
    public static final String KEY_API_KEY = "apiKey";
    public static final String KEY_PHONE1 = "p1";
    public static final String KEY_COMPANY_NAME = "c";
    public static final String KEY_SALT = "salt";
    public static final String KEY_ADMIN = "admin";
    public static final String KEY_LOGIN_STATUS = "loginStatus";
    public static final String KEY_REPO_METADATA = "repoMetadata";
    public static final String KEY_ADDRESSES = "addresses";
    public static final String KEY_CLIENT_INFORMATION = "clientInformation";
    public static final String KEY_API_KEY_METADATA = "am";
    public static final String KEY_API_KEY_PENDING_APPROVAL = "pa";
    public static final String KEY_PERMISSIONS = "prm";
    public static final String KEY_FAVORITES = "fav";
    public static final String KEY_AGGREGATE_PERMISSIONS = "agp";
    public static final String KEY_UDID = "udid";

    public static final String KEY_ATTR_ACTIVE_REC_SCREEN_STYLE_ID = "activeRSStyle";

    private static final int RANDOM_PASSWORD_LENGTH = 64;
    private static final int MAX_SESSION_TOKENS = 10;

    @Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String firstName;

    @Getter
    @Setter
    @ApiObjectField(description = "Last Name")
    private String lastName;

    @Getter
    @Setter
    @ApiObjectField(description = "Title")
    private String title;

   
    @Getter
    // custom setter below
    @NonNull
    @Field(KEY_EMAIL)
    @ApiObjectField(description = "Email Address")
    @Indexed(unique = true)
    private String email;

    @Getter
    // custom setter below
    @NonNull
    @ApiObjectField(description = "Password")
    private String password;
   
    @Getter
    @Setter
    @Field(KEY_SALT)
    @ApiObjectField(description = "Salt")
    private String salt;

    @Getter
    @Setter
    @Field(KEY_ACTIVE)
    @ApiObjectField(description = "States whether the user has an active account")
    private boolean active = true;

    @Getter
    @Setter
    @Field(KEY_ADMIN)
    @ApiObjectField(description = "States Whether User Is Administrator")
    private boolean admin;

    @Getter
    @Setter
    @Field(KEY_LAST_LOGIN_TIME)
    @ApiObjectField(description = "Last time the user logged in")
    private Date lastLoginTime;

    @Getter
    @Setter
    @Field(KEY_LOGIN_STATUS)
    @ApiObjectField(description = "Login Status")
    private int loginStatus;

    @Getter
    @Setter
    @Field(KEY_FIRST_TIME_USER)
    @ApiObjectField(description = "True if the user has never logged in before")
    private boolean firstTimeUser;

    @Getter
    @Setter
    @Field(KEY_VERIFICATION_LINK_ID)
    @ApiObjectField(description = "Verification link id")
    private String verificationLinkId;

  

    @Getter
    @Setter
    @Indexed  // not unique because most will be null values
    @Field(KEY_API_KEY)
    private String apiKey;

    @Getter
    @Setter
    @Field(KEY_COMPANY_NAME)
    private String companyName;

    @Getter
    @Setter
    @Field(KEY_PHONE1)
    private String phone1;

	@Override
	public boolean containsKey(String attributeName) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
