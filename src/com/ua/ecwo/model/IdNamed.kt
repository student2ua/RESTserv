package com.ua.ecwo.model

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
data class IdNamed (@XmlElement var id:Int, @XmlElement var name:String)
