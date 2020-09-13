package com.darkness.controller;

import java.io.Serializable;
import java.util.Map;

public class requestForm implements Serializable {

    private static final long serialVersionUID = -8848626608893337340L;

    private String templateId;
    private Map<String, String> values;
    private String filename;
    private String folderId;

}
