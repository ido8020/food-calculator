package com.rubypaper.guestbook;

import java.util.List;

public interface GuestbookService {
	void insert100(GuestbookVO vo);
	List<GuestbookVO> GuestbookList(GuestbookVO vo);

	int totalcount(GuestbookVO vo);

}
