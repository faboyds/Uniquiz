//
//  Properties.swift
//  UniQuiziOS
//
//  Created by Pedro Neto on 08/09/17.
//  Copyright © 2017 Phoenix. All rights reserved.
//

import Foundation


class Properties {
    
    static var user : User?
    
    fileprivate static func  getURLBase() -> String {
        return "http://8f76071b.ngrok.io/uniquiz"
    }
    
    static func getSubjectURL() -> String {
        return getURLBase() + "/subjects"
    }
    
    static func getFieldsURL() -> String{
        return getURLBase() + "/fields"
    }
    
    static func getLoginURL() -> String  {
        return getURLBase() + "/login"
    }
    
    static func getSearchURL(toSearch : String ) -> String {
        return getURLBase() + "/quizzes/quiz?title=\(toSearch)"
    }
    
    static func getQuizFromCourseURL(course : Int) -> String{
        return getURLBase() +  "/quizzes?coursePk=\(course)"
    }
    static func getQuizFromCourseURL(subject : Int) -> String{
        return getURLBase() +  "/quizzes?subjectPk=\(subject)"
    }
    
    static func getCourseURL(field : Int ) -> String {
        return getURLBase() + "/courses?fieldPk=\(field)"
    }
    
    static func getUser(user : String) -> String {
        return getURLBase() + "/users/\(user)"
    }
    
    static func getSolutionsURL(quizPk: String, user :  String) -> String {
        return getURLBase() + "/solutions/latest?quiz=\(quizPk)&email=\(user)"
    }
    static func getQuizURL(quizPk: String) -> String {
        return getURLBase() + "/quizzes/\(quizPk)"
    }
    
    static func getPostSolutionURL() -> String {
        return getURLBase() + "/solutions"
    }
}
