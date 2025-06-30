//
//  FontExtension.swift
//  Travel App
//
//  Created by FIONA Patricia on 24/04/25.
//

import SwiftUI


private let calSansFont = "CalSans-Regular"
private let interFont = "Inter-Regular"

private let title01Size = CGFloat(28)
private let title02Size = CGFloat(24)
private let title03Size = CGFloat(20)

private let medium01Size = CGFloat(16)
private let medium02Size = CGFloat(18)

private let subtitleSize = CGFloat(14)

private let paragraphSize = CGFloat(12)

extension Font{
    static let CalSans_Title01 = Font.custom(calSansFont,size: title01Size)
    static let CalSans_Title02 = Font.custom(calSansFont,size: title02Size)
    static let CalSans_Subtitle = Font.custom(calSansFont,size: subtitleSize)
    static let CalSans_Paragraph = Font.custom(calSansFont,size: paragraphSize)
    
    static let Inter_Title01 = Font.custom(interFont,size: title01Size)
    static let Inter_Title02 = Font.custom(interFont,size: title02Size)
    static let Inter_Title03 = Font.custom(interFont,size: title03Size)
    static let Inter_Medium01 = Font.custom(interFont,size: medium01Size)
    static let Inter_Medium02 = Font.custom(interFont,size: medium02Size)
    static let Inter_Subtitle = Font.custom(interFont,size: subtitleSize)
    static let Inter_Paragraph = Font.custom(interFont,size: paragraphSize)
}
