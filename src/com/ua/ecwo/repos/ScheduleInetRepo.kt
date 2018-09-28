package com.ua.ecwo.repos

interface ScheduleInetRepo {

    fun getFacultyAll(): Set<IdNamed>

    fun getFaculty(fid: Int): IdNamed

    fun getSpecialityAll(fid: Int): List<IdNamed>

    fun getSpeciality(fid: Int, sid: Int): IdNamed

    fun getCourses(fid: Int, sid: Int): List<Int>

    fun getStudentGroups(fid: Int, sid: Int, course: Int): Set<IdNamed>

    fun getStudentGroups(fid: Int, sid: Int, course: Int,sgId: Int): IdNamed
}
