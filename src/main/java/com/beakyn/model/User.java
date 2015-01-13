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

import com.stormpath.sdk.account.Account;


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
    private static final String KEY_GROUP = "group";
    
    public static final String KEY_ATTR_ACTIVE_REC_SCREEN_STYLE_ID = "activeRSStyle";

    private static final int RANDOM_PASSWORD_LENGTH = 64;
    private static final int MAX_SESSION_TOKENS = 10;
    

    @ApiObjectField(description = "First Name")
    private String firstName;

    @ApiObjectField(description = "Username i.e. unique identifier for the user")
    private String userName;

    
    @ApiObjectField(description = "Last Name")
    private String lastName;

    @Getter
    @Setter
    @ApiObjectField(description = "Title")
    private String title;

   
    // custom setter below
    @NonNull
    @Field(KEY_EMAIL)
    @ApiObjectField(description = "Email Address")
    @Indexed(unique = true)
    private String email;

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
    
    @Field(KEY_GROUP)
    private String groupUrl;

    private Account account;

    private String sptoken;
    
    private String groupName;
    
    @Getter
    @Setter
    private String confirmPassword;
    
    
    
    public User(Account account) {
        if (account != null) {
            setEmail(account.getEmail());
            setFirstName(account.getGivenName());
            setLastName(account.getSurname());
            setUserName(account.getUsername());
            //setAccount(account);
        }
    }



	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean containsKey(String attributeName) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	public String getGroupName() {
		return groupName;
	}



	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Account getAccount() {
		return account;
	}



	public void setAccount(Account account) {
		this.account = account;
	}



	public String getConfirmPassword() {
		return confirmPassword;
	}



	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getSptoken() {
		return sptoken;
	}



	public void setSptoken(String sptoken) {
		this.sptoken = sptoken;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getGroupUrl() {
		return groupUrl;
	}



	public void setGroupUrl(String groupUrl) {
		this.groupUrl = groupUrl;
	}

	
}
