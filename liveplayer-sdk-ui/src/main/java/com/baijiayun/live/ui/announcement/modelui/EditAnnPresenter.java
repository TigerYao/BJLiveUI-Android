package com.baijiayun.live.ui.announcement.modelui;

import com.baijiayun.live.ui.R;
import com.baijiayun.live.ui.activity.LiveRoomRouterListener;
import com.baijiayun.live.ui.utils.RxUtils;
import com.baijiayun.livecore.models.imodels.IAnnouncementModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 *  公告/分组编辑
 *  panzq
 *  20190708
 */
public class EditAnnPresenter implements EditAnnContract.Presenter, IAnnouncementUI{

    private boolean isGroup = true;
    private EditAnnContract.View mView;
    private LiveRoomRouterListener mRouter;

    private Disposable subscriptionOfAnnouncementChange;
    private IAnnouncementModel iAnnModel;

    public EditAnnPresenter(EditAnnContract.View view, boolean isGroup, IAnnouncementModel iAnnouncementModel) {
        this.mView = view;
        this.isGroup = isGroup;
        this.iAnnModel = iAnnouncementModel;
    }

    @Override
    public void setRouter(LiveRoomRouterListener liveRoomRouterListener) {
        this.mRouter = liveRoomRouterListener;
    }

    @Override
    public void subscribe() {

//        subscriptionOfAnnouncementChange = mRouter.getLiveRoom().getObservableOfAnnouncementChange()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<IAnnouncementModel>() {
//                    @Override
//                    public void accept(IAnnouncementModel iAnnouncementModel) {
//
//                        if (iAnnouncementModel == null)
//                            return;
//
//                        if (!String.valueOf(mRouter.getLiveRoom().getGroupId()).equals(iAnnouncementModel.getGroup())) {
//                            return;
//                        }
//
//                        NoticeInfo info = mView.getNoticeInfo();
//
//                        if (info.content != null && info.content.equals(iAnnouncementModel.getContent())) {
//                            //修改成功
//                            mRouter.showMessage(R.string.string_notice_context_suss);
//                        } else {
//                            //
//                            mRouter.showMessage(R.string.string_notice_context_error);
//                        }
//                    }
//                });
        mView.initInfo(iAnnModel);
    }

    @Override
    public void unSubscribe() {
//        RxUtils.dispose(subscriptionOfAnnouncementChange);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void setNoticeInfo(IAnnouncementModel iAnnouncementModel) {

    }

    @Override
    public NoticeInfo getNotice() {
        return mView.getNoticeInfo();
    }
}
