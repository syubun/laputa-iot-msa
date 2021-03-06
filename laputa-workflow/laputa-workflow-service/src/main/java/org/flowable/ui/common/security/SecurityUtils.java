/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.ui.common.security;

import cn.hutool.core.util.StrUtil;
import com.laputa.iot.common.core.constant.SecurityConstants;
import com.laputa.iot.common.security.service.LaputaUser;
import org.flowable.idm.api.User;
import org.flowable.ui.common.model.RemoteUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Utility class for Spring Security.
 */
public class SecurityUtils {

    private static User assumeUser;

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     */
    public static String getCurrentUserId() {
        User user = getCurrentUserObject();
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     * @param authentication
     * @return LaputaUser
     * <p>
     */
    public LaputaUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof LaputaUser) {
            return (LaputaUser) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public LaputaUser getUser() {
        Authentication authentication = getAuthentication();
        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     * @return 角色集合
     */
    public List<Long> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<Long> roleIds = new ArrayList<>();
        authorities.stream().filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE))
                .forEach(granted -> {
                    String id = StrUtil.removePrefix(granted.getAuthority(), SecurityConstants.ROLE);
                    roleIds.add(Long.parseLong(id));
                });
        return roleIds;
    }

    /**
     * @return the {@link User} object associated with the current logged in user.
     */
    public static User getCurrentUserObject() {
        RemoteUser user = new RemoteUser();
        user.setId("admin");
        user.setDisplayName("POLARIS");
        user.setFirstName("WANG");
        user.setLastName("POLARIS");
        user.setEmail("admin@flowable.com");
        user.setPassword("123456");
        List<String> pris = new ArrayList<>();
        pris.add(DefaultPrivileges.ACCESS_MODELER);
        pris.add(DefaultPrivileges.ACCESS_IDM);
        pris.add(DefaultPrivileges.ACCESS_ADMIN);
        pris.add(DefaultPrivileges.ACCESS_TASK);
        pris.add(DefaultPrivileges.ACCESS_REST_API);
        user.setPrivileges(pris);
        return user;
    }

//    public static FlowableAppUser getCurrentFlowableAppUser() {
//        FlowableAppUser user = null;
//
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        if (securityContext != null && securityContext.getAuthentication() != null) {
//            Object principal = securityContext.getAuthentication().getPrincipal();
//            if (principal instanceof FlowableAppUser) {
//                user = (FlowableAppUser) principal;
//            }
//        }
//        return user;
//    }
//
//    public static boolean currentUserHasCapability(String capability) {
//        FlowableAppUser user = getCurrentFlowableAppUser();
//        for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
//            if (capability.equals(grantedAuthority.getAuthority())) {
//                return true;
//            }
//        }
//        return false;
//    }

    public static void assumeUser(User user) {
        assumeUser = user;
    }

    public static void clearAssumeUser() {
        assumeUser = null;
    }

}
