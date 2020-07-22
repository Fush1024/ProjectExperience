package com.quickly.devploment.groovy.repay;

/**
 * @Author lidengjin
 * @Date 2020/7/22 9:27 上午
 * @Version 1.0
 */
public class FormulaEntity {
	private Integer version;
	private String groovyScript;

	private String formulaNo;

	public String getFormulaNo() {
		return formulaNo;
	}

	public void setFormulaNo(String formulaNo) {
		this.formulaNo = formulaNo;
	}

	public FormulaEntity() {
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getGroovyScript() {
		return groovyScript;
	}

	public void setGroovyScript(String groovyScript) {
		this.groovyScript = groovyScript;
	}
}
