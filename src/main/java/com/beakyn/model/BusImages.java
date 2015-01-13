package com.beakyn.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.DateTime;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.data.annotation.TypeAlias;


@ToString
@EqualsAndHashCode(callSuper = false, of = { "public_id" })
@TypeAlias("busImages")
@ApiObject(name = "BusinessImage")
public class BusImages extends AbstractDocument implements AttributesDocument  {

	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String public_id;

	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String version;
	

	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String signature;


	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private long width;
	

	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private long height;


	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String format;


	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String resource_type;


	/*@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private DateTime created_at;

	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private DateTime lastModifiedDate;*/


	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private long bytes;

	

	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String type;


	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String etag;
	

	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String url;


	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String secure_url;
	

	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String businessId;


	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String name;
	
	@Getter
    @Setter
    @ApiObjectField(description = "First Name")
    private String description;
	
	


	@Override
	public boolean containsKey(String attributeName) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
