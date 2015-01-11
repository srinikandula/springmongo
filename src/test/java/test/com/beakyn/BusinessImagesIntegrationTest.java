package test.com.beakyn;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.beakyn.config.AbstractDAOIntegrationTest;
import com.beakyn.dao.BusinessImagesDAO;
import com.beakyn.model.BusImages;

public class BusinessImagesIntegrationTest extends AbstractDAOIntegrationTest {
	
	@Autowired
	private BusinessImagesDAO businessImagesDAO;
	
	
	@Test
	public void getAllImages() {
		try{
		Iterable<BusImages> images = businessImagesDAO.findAll();
		int notFound = 0;
		for(BusImages image:images){
			File businessFolder = new File("/Users/skandula/Downloads/gwcImages/"+image.getBusinessId());
			File imageFolder = new File("/Users/skandula/Downloads/gwcImages/"+image.getBusinessId()+"/"+image.getId());
			if(!businessFolder.exists()){
				System.out.println(businessFolder.mkdir());
			}
			if(!imageFolder.exists())
				System.out.println(imageFolder.mkdir());
			if(StringUtils.isBlank(image.getUrl()) || StringUtils.isBlank(image.getSecure_url())){
				continue;
			}
			String fileName = image.getUrl().substring(image.getUrl().lastIndexOf("/")+1);
			File imageFile = new File(imageFolder.getAbsolutePath()+"/"+fileName);
			if(imageFile.exists()){	
				//System.out.println("Downloaded image "+imageFile.getAbsolutePath());
				continue;
			}
			URL url = new URL(image.getUrl());
			//System.out.println("downloading "+url.getPath() +" " + image.getUrl());
			InputStream in = null;
			try{
				in = new BufferedInputStream(url.openStream());
			}catch(IOException e){
				notFound++;
				//System.err.println("Not Found "+image.getUrl());
				continue;
			}
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1!=(n=in.read(buf)))
			{
			   out.write(buf, 0, n);
			}
			out.close();
			in.close();
			byte[] response = out.toByteArray();
			imageFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(imageFile);
			fos.write(response);
			fos.close();
			
		}
		System.out.println("Not found count "+notFound);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
