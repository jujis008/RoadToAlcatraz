package com.armoz.roadtoalcatraz;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.path.android.jobqueue.JobManager;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;

/**
 * Created by ruben.arana on 25/12/15.
 */
public abstract class AbstractJob {

	@Mock
	MainThread mainThread;

	@Mock
	JobManager jobManager;

	@Mock
	protected DomainErrorHandler domainErrorHandler;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				Runnable runnable = (Runnable) invocation.getArguments()[0];
				runnable.run();
				return null;
			}
		}).when(mainThread).post(any(Runnable.class));
	}
}
