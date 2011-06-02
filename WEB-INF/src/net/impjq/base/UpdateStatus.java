
package net.impjq.base;

/**
 * Update Status interface
 * 
 * @author pjq
 */
public abstract class UpdateStatus extends BaseHttpServlet {
    /**
	 * 
	 */
    private static final long serialVersionUID = -835511669272745216L;

    protected abstract void updateStatus(String userName, String password,
            String message);



 

}
