//
//  ModelObjects.swift
//  UniQuiziOS
//
//  Created by Pedro Neto on 08/09/17.
//  Copyright © 2017 Phoenix. All rights reserved.
//

import Foundation




struct Answer  : Codable {
    let pk : Int
    let answer : String
    let rightAnswer : Bool
}

struct Course : Codable {
    let pk : Int
    let courseName  : String
    let fieldName : String
    let fieldPk : Int
}

struct Field : Codable {
    let pk : Int
    let fieldName : String
}

struct Question : Codable {
    let pk : UInt64
    let question : String
    let answers : [Answer]
    let justification : String
}

struct Quiz : Codable {
    let pk : Int
    let questions : [Question]
    let difficulty :  String
    let subjectPk : Int
    let coursePk : Int
    let courseName : String
    let subjectName : String
    let title : String
    let averageRating : Double
    let author : String
}
struct SimplifiedQuiz  : Codable {
    let pk : Int
    let difficulty :  String
    let subjectPk : Int
    let coursePk : Int
    let subjectName : String
    let title : String
    let averageRating : Double
    let author : String
    
    init (_ quiz: Quiz){
        self.pk = quiz.pk
        self.coursePk = quiz.coursePk
        self.difficulty = quiz.difficulty
        self.subjectName = quiz.subjectName
        self.title = quiz.title
        self.subjectPk = quiz.subjectPk
        self.averageRating = quiz.averageRating
        self.author = quiz.author
    }
}


struct Solution : Codable {
    let pk : Int
    let quizPk : Int
    let email :  String
    let rightAnswers : Int
    let wrongAnswers : Int
    
}

struct SolutionPost : Codable {
    var quizPk : Int
    var email :  String
    var rightAnswers : Int
    var wrongAnswers : Int
    
    init(username : String , quizPk : Int){
        self.email = username
        self.quizPk = quizPk
        self.rightAnswers = 0
        self.wrongAnswers = 0
    }
}


struct Subject : Codable{
    let pk : Int
    let subjectName : String
}

struct User : Codable {
    let username : String
    let password : String
    let email :  String
    let name :  String
    let roles : [String]
    let userStatistics : UserStatistics
}
struct UserStatistics : Codable {
    let totalRightAnswers : Int
    let totalWrongAnswers : Int
    let totalQuizzesSolved : Int
    let totalQuizzesPassed : Int
}



