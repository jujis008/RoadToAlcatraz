package com.armoz.roadtoalcatraz.tournament.domain.usercase;


import android.content.Context;

import com.armoz.roadtoalcatraz.tournament.domain.callback.PrepareTournamentCallback;

public interface PrepareTournament {

    void prepareNextTournament(PrepareTournamentCallback callback, Context context);
}
