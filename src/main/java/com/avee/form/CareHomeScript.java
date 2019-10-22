package com.avee.form;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class CareHomeScript implements java.io.Serializable {
	private List<ScriptItems> script = new ArrayList<ScriptItems>();

	public CareHomeScript() {
		super();
	}

	public List<ScriptItems> getScript() {
		return script;
	}

	public void setScript(List<ScriptItems> script) {
		this.script = script;
	}

}
