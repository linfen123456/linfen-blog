package cn.linfenw.modules.security.social.github.connect;

import cn.linfenw.modules.security.social.github.api.GitHub;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Classname GiteeAdapter
 * @Description
 * @Author linfen
 * @Date 2019-07-08 21:49
 * @Version 1.0
 */
public class GitHubConnectionFactory extends OAuth2ConnectionFactory<GitHub> {

	public GitHubConnectionFactory(String providerId, String clientId, String clientSecret) {
		super(providerId, new GitHubServiceProvider(clientId, clientSecret), new GitHubAdapter());
	}
}
