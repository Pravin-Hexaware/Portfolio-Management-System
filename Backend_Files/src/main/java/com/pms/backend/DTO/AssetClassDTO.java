package com.pms.backend.DTO;
public class AssetClassDTO {
    private Long id;
    private String className;
    private String subClassName;

    public AssetClassDTO(Long id, String className, String subClassName) {
        this.id = id;
        this.className = className;
        this.subClassName = subClassName;
    }

	public AssetClassDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSubClassName() {
		return subClassName;
	}

	public void setSubClassName(String subClassName) {
		this.subClassName = subClassName;
	}
    
    
}