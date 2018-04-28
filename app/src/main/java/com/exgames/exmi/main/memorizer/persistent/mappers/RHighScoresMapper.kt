package com.exgames.exmi.main.memorizer.persistent.mappers

import com.exgames.exmi.main.memorizer.persistent.domain.HighScores
import com.exgames.exmi.main.memorizer.persistent.realm_entities.RHighScores


class RHighScoresMapper : RealmMapper<HighScores, RHighScores> {
    override fun transform(input: HighScores): RHighScores {
        val output: RHighScores = RHighScores()
        output.score = input.score
        output.userName = input.userName
        return output
    }

    override fun transform(input: RHighScores): HighScores {
        val output: HighScores = HighScores("", "")
        output.score = input.score
        output.userName = input.userName
        return output
    }
}