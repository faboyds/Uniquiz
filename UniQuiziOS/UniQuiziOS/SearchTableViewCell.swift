//
//  SearchTableViewCell.swift
//  UniQuiziOS
//
//  Created by Pedro Neto on 08/09/17.
//  Copyright © 2017 Phoenix. All rights reserved.
//

import UIKit

class SearchTableViewCell: UITableViewCell {

    @IBOutlet weak var quizTheme: UILabel!
    @IBOutlet weak var quizName: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    

}
