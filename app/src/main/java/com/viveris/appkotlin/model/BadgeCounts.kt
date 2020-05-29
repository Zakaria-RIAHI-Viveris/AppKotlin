package com.viveris.appkotlin.model

import java.io.Serializable

class BadgeCounts(
        val bronze: Int = 0,
        val silver: Int = 0,
        val gold: Int = 0
) : Serializable
