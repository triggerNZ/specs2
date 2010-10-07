package org.specs2
package form

class FieldSpec extends SpecificationWithJUnit {
  val content =
"""
  A Field is a labelled property with can be embedded in a Form.
"""                                                               ^
" A Field can be created"                                         ^
"   from just a value (then the name is empty)"                   ! creation.e1^
"   from a name and a value"                                      ! creation.e2^
"   from existing fields, concatenating them"                     ! creation.e3^
" A Field can be executed"                                        ^
"   it returns a Success"                                         ! execute.e1^
" A Field can be modified"                                        ^
"   to a string Field"                                            ! modify.e1^
                                                                  end

  val name = Field("name", "eric")
  val age = Field("age", 18)
  
  case object creation {
    def e1 = Field(18).label must_== ""
    def e2 = age() must_== 18 
    def e3 = {
      Field("person", name, age).toString must_== "person: eric/18"
    }
  }
  case object execute {
    def e1 = age.execute must_== success
  }
  case object modify {
    def e1 = age.toStringField() must_== "18"
  }
}