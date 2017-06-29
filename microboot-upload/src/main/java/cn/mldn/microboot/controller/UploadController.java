package cn.mldn.microboot.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.mldn.microboot.util.controller.AbstractBaseController;

@Controller
public class UploadController extends AbstractBaseController {
	@RequestMapping(value = "/uploadPre", method = RequestMethod.GET)
	public String uploadPre() { // 通过model可以实现内容的传递
		return "upload_page";
	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(String groupId, String fileId,Model model) throws Exception {
		// 通过ClassPath路径获取要使用的配置文件
		ClassPathResource classPathResource = new ClassPathResource(
				"fastdfs_client.conf");
		// 进行客户端访问的整体配置，需要知道配置文件的完整路径
		ClientGlobal.init(classPathResource.getClassLoader()
				.getResource("fastdfs_client.conf").getPath());
		// FastDFS的核心操作在于tracker处理上，所以此时需要定义Tracker客户端
		TrackerClient trackerClient = new TrackerClient();
		// 定义TrackerServer的配置信息
		TrackerServer trackerServer = trackerClient.getConnection();
		int ts = (int) (System.currentTimeMillis() / 1000);// 时间参考
		StringBuffer fileUrl = new StringBuffer();
		fileUrl.append("http://");
		fileUrl.append("fastdfs-tracker");
		fileUrl.append("/" + groupId + "/").append(fileId);
		fileUrl.append("?token=").append(
				ProtoCommon.getToken(fileId, ts, ClientGlobal.g_secret_key));
		fileUrl.append("&ts=").append(ts);
		System.out.println(fileUrl);
		trackerServer.close();
		model.addAttribute("image", fileUrl) ;
		return "upload_show" ;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(String name, HttpServletRequest request)
			throws Exception {
		if (request instanceof MultipartHttpServletRequest) { // 如果你现在是MultipartHttpServletRequest对象
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = mrequest.getFiles("photo");
			Iterator<MultipartFile> iter = files.iterator();
			while (iter.hasNext()) {
				MultipartFile photo = iter.next();
				if (photo != null) { // 现在有文件上传
					// 如果要想进行上传则一定需要获取到文件的扩展名称
					String fileExtName = photo.getContentType().substring(
							photo.getContentType().lastIndexOf("/") + 1);
					// 通过ClassPath路径获取要使用的配置文件
					ClassPathResource classPathResource = new ClassPathResource(
							"fastdfs_client.conf");
					// 进行客户端访问的整体配置，需要知道配置文件的完整路径
					ClientGlobal.init(classPathResource.getClassLoader()
							.getResource("fastdfs_client.conf").getPath());
					// FastDFS的核心操作在于tracker处理上，所以此时需要定义Tracker客户端
					TrackerClient trackerClient = new TrackerClient();
					// 定义TrackerServer的配置信息
					TrackerServer trackerServer = trackerClient.getConnection();
					// 在整个FastDFS之中真正负责干活的就是Storage
					StorageServer storageServer = null;
					StorageClient1 storageClient = new StorageClient1(
							trackerServer, storageServer);
					// 定义上传文件的元数据
					NameValuePair[] metaList = new NameValuePair[3];
					metaList[0] = new NameValuePair("fileName",
							photo.getOriginalFilename());
					metaList[1] = new NameValuePair("fileExtName", fileExtName);
					metaList[2] = new NameValuePair("fileLength",
							String.valueOf(photo.getSize()));
					// 如果要上传则使用trackerClient对象完成
					String upload_file = storageClient.upload_file1(
							photo.getBytes(), fileExtName, metaList);
					System.out.println(upload_file);
					trackerServer.close();
				}
			}
		}
		return "upload-file";
	}
}
