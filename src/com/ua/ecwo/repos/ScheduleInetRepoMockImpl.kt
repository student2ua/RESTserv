package com.ua.ecwo.repos

import com.ua.ecwo.model.IdNamed

class ScheduleInetRepoMockImpl : ScheduleInetRepo {
    val courses: Set<Int> = setOf(1, 2, 3, 4)
    val maps = mapOf(
            Pair(IdNamed(1, "faculty_1"), listOf(IdNamed(1, "sf"), IdNamed(2, "sE"))),
            Pair(IdNamed(2, "faculty_2"), listOf(IdNamed(3, "sf"), IdNamed(4, "sE")))

    )
    val studentGroups: Map<Triple<Int, Int, Int>, Set<IdNamed>> = mapOf(
            Pair(Triple(1, 1, 1), setOf(IdNamed(1, "Test_1_SG"), IdNamed(2, "Test_2_SG"))),
            Pair(Triple(1, 1, 2), setOf(IdNamed(3, "Test_3_SG"), IdNamed(4, "Test_4_SG"))),
            Pair(Triple(1, 1, 3), setOf(IdNamed(5, "Test_5_SG"), IdNamed(6, "Test_6_SG"))),
            Pair(Triple(1, 1, 4), setOf(IdNamed(7, "Test_7_SG"), IdNamed(8, "Test_8_SG"))),
            Pair(Triple(1, 2, 1), setOf(IdNamed(9, "Test_9_SG"), IdNamed(10, "Test_10_SG"))),
            Pair(Triple(1, 2, 2), setOf(IdNamed(11, "Test_11_SG"), IdNamed(12, "Test_12_SG"))),
            Pair(Triple(2, 3, 1), setOf(IdNamed(13, "Test_11_SG"), IdNamed(14, "Test_12_SG")))
    )

    override fun getFacultyAll(): Set<IdNamed> {
        return maps.keys;
    }

    override fun getFaculty(fid: Int): IdNamed {
        return maps.keys.single { idNamed -> idNamed.id == fid }
    }

    override fun getSpecialityAll(fid: Int): List<IdNamed> {
        return maps.getOrDefault(maps.keys.single { idNamed -> idNamed.id == fid }, emptyList())
    }

    override fun getSpeciality(fid: Int, sid: Int): IdNamed {
        return maps.getOrDefault(maps.keys.single { idNamed -> idNamed.id == fid }, emptyList()).single { idNamed -> idNamed.id == sid }
    }

    override fun getCourses(fid: Int, sid: Int): Set<Int> {
        return courses;
    }

    override fun getStudentGroups(fid: Int, sid: Int, course: Int): Set<IdNamed> {
        return studentGroups.getOrDefault(Triple(fid, sid, course), emptySet())
    }

    override fun getStudentGroups(fid: Int, sid: Int, course: Int,sgId: Int): IdNamed {
       return studentGroups.getOrDefault(Triple(fid, sid, course), emptySet()).single { idNamed -> idNamed.id == sid  }
    }
}