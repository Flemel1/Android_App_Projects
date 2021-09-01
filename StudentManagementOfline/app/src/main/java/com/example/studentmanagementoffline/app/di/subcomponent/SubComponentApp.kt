package com.example.studentmanagementoffline.app.di.subcomponent

import com.example.studentmanagementoffline.app.di.subcomponent.module.ModuleMapel
import com.example.studentmanagementoffline.app.di.subcomponent.module.HomeFragmentScope
import com.example.studentmanagementoffline.app.di.subcomponent.module.ModuleKelas
import com.example.studentmanagementoffline.app.di.subcomponent.module.ModuleNilai
import com.example.studentmanagementoffline.app.di.subcomponent.module.ModuleSiswa
import com.example.studentmanagementoffline.app.di.subcomponent.module.ModuleTugas
import com.example.studentmanagementoffline.app.presentation.*
import dagger.Subcomponent

@HomeFragmentScope
@Subcomponent(
    modules = [ModuleKelas::class,
        ModuleMapel::class,
        ModuleNilai::class,
        ModuleSiswa::class,
        ModuleTugas::class]
)
interface SubComponentApp {
    fun inject(homeFragment: HomeFragment)
    fun inject(addClassFragment: AddClassFragment)
    fun inject(classFragment: ClassFragment)
    fun inject(addMapelFragment: AddMapelFragment)
    fun inject(addAssignmentFragment: AddAssignmentFragment)
    fun inject(assignmentFragment: AssignmentFragment)
    fun inject(mapelFragment: MapelFragment)
    fun inject(listStudentFragment: ListStudentFragment)
    fun inject(addStudentFragment: AddStudentFragment)
    fun inject(addValueAssignmentFragment: AddValueAssignmentFragment)
    fun inject(scoreFragment: ScoreFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SubComponentApp
    }
}