package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 小程序用户
 * 

 */
@TableName("tb_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户名
	 */
	private String realName;
	/**
	 * 车牌号码
	 */
	private String plate;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 身份证号码
	 */
	private Integer cardId;
	/**
	 * OpenID
	 */
	private Long openId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建时间
	 */
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：用户名
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * 获取：用户名
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * 设置：车牌号码
	 */
	public void setPlate(String plate) {
		this.plate = plate;
	}
	/**
	 * 获取：车牌号码
	 */
	public String getPlate() {
		return plate;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：身份证号码
	 */
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	/**
	 * 获取：身份证号码
	 */
	public Integer getCardId() {
		return cardId;
	}
	/**
	 * 设置：OpenID
	 */
	public void setOpenId(Long openId) {
		this.openId = openId;
	}
	/**
	 * 获取：OpenID
	 */
	public Long getOpenId() {
		return openId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
