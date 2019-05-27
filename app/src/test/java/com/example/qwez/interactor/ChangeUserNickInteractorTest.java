package com.example.qwez.interactor;

import com.example.qwez.RxResources;
import com.example.qwez.repository.firebase.FirebaseAuthRepositoryType;
import com.example.qwez.repository.firebase.FirebaseDatabaseType;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Completable;
import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class ChangeUserNickInteractorTest {

    @ClassRule
    public static RxResources rxres = new RxResources();

    @Mock
    FirebaseAuthRepositoryType firebaseAuthRepositoryType;
    @Mock
    FirebaseDatabaseType firebaseDatabaseType;
    @Mock
    FirebaseUser firebaseUser;

    private static final String UID = "123";

    @InjectMocks
    ChangeUserNickInteractor changeUserNickInteractor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void changeNick() {
        when(firebaseAuthRepositoryType.getCurrentUser()).thenReturn(Observable.just(firebaseUser));
        when(firebaseUser.getUid()).thenReturn(UID);
        when(firebaseAuthRepositoryType.changeUserNick(firebaseUser,"hey")).thenReturn(Completable.complete());
        when(firebaseDatabaseType.updateNick(UID, "hey")).thenReturn(Completable.complete());

        changeUserNickInteractor.ChangeNick("hey")
                .test()
                .assertNoErrors()
                .assertComplete();

        verify(firebaseAuthRepositoryType).changeUserNick(firebaseUser, "hey");
        verify(firebaseDatabaseType).updateNick(UID, "hey");

    }
}